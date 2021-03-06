package com.linsz.demo.service.impl;

import com.linsz.demo.dao.AyUserDao;
import com.linsz.demo.error.BusinessException;
import com.linsz.demo.model.AyUser;
import com.linsz.demo.repository.AyUserRepository;
import com.linsz.demo.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

//用户服务层实现类
@Transactional
@Service
public class AyUserServiceImpl implements AyUserService {

    @Resource (name="ayUserRepository")
    private AyUserRepository ayUserRepository;
    @Resource
    private  RedisTemplate redisTemplate;
    private static final String ALL_USER="ALL_USER_LIST";
    public AyUser findById(String id){
        //step.1  查询Redis缓存中的所有数据
        List<AyUser> ayUserList = redisTemplate.opsForList().range(ALL_USER, 0, -1);
        if(ayUserList != null && ayUserList.size() > 0){
            for(AyUser user : ayUserList){
                if (user.getId().equals(id)){
                    return user;
                }
            }
        }
        //step.2  查询数据库中的数据
        AyUser ayUser = ayUserRepository.findById(id).get();
        if(ayUser != null){
            //step.3 将数据插入到Redis缓存中
            redisTemplate.opsForList().leftPush(ALL_USER, ayUser);
        }
        return ayUser;
    }
//    @Override
//    public List<AyUser> findAll(){
//        return ayUserRepository.findAll();
//    }
    @Transactional
    @Override
    public AyUser save(AyUser ayUser) {
//        AyUser saveUser=ayUserRepository.save(ayUser);
//        //出现空指针
//        String  error=null;
//        error.split("/");
//        return saveUser;
        return ayUserRepository.save(ayUser);


    }
    Logger logger= LogManager.getLogger(this.getClass());
    @Override
    public void delete(String id){
        ayUserRepository.deleteById(id);
        logger.info("userId:"+id+"用户被删除");
    }
    @Override
    public Page<AyUser> findAll(Pageable pageable){
        return ayUserRepository.findAll(pageable);
    }
    @Override
    public List<AyUser>findByName(String name){
        return ayUserRepository.findByName(name);
    }
    @Override
    public List<AyUser>findByNameLike(String name){
        return ayUserRepository.findByNameLike(name);
    }
    @Override
    public List<AyUser>findByIdIn(Collection<String>ids){
        return ayUserRepository.findByIdIn(ids);

    }
    @Resource
    private AyUserDao ayUserDao;
    @Override
    public AyUser findByNameAndPassword(String name,String password){
        return ayUserDao.findByNameAndPassword(name, password);
    }

    @Override
    public List<AyUser> findAll() {
        try{
            System.out.println("开始做任务");
            long start = System.currentTimeMillis();
            List<AyUser> ayUserList = ayUserRepository.findAll();
            long end = System.currentTimeMillis();
            System.out.println("完成任务，耗时：" + (end - start) + "毫秒");
            return ayUserList;
        }catch (Exception e){
            logger.error("method [findAll] error",e);
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    @Async
    public Future<List<AyUser>> findAsynAll() {
        try{
            System.out.println("开始做任务");
            long start = System.currentTimeMillis();
            List<AyUser> ayUserList = ayUserRepository.findAll();
            long end = System.currentTimeMillis();
            System.out.println("完成任务，耗时：" + (end - start) + "毫秒");
            return new AsyncResult<List<AyUser>>(ayUserList) ;
        }catch (Exception e){
            logger.error("method [findAll] error",e);
            return new AsyncResult<List<AyUser>>(null);
        }
    }
    @Override
    @Retryable(value={BusinessException.class}, maxAttempts=5,
    backoff=@Backoff(delay=5000,multiplier = 2))
    public AyUser findByNameAndPasswordRetry(String name,String password){
        System.out.println("[findByNameAndRetry]方法失败重试了！");
        throw new BusinessException();
    }

    @Override
    public AyUser findByUserName(String name) {
        return ayUserDao.findByUserName(name);
    }




}

public class HelloWorld {
    private String name;
    HelloWorld(){
        System.out.println("This is HelloWorld Constructor!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("This is HelloWorld setName()");
        this.name = name;
    }
    public void SayHello(){
        System.out.println("Hello"+name);
    }
}

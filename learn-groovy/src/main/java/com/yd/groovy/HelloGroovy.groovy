package main.java.com.yd.groovy

class helloGroovy //implements GenericService
{

//    @Override
    Object invoke(String method, String[] parameterTypes, Object[] args) {
        return "reply from helloGroovy " + method + " " + args[0];
    }

    Object say(String name) {
        println("hello " + name);
        return "hello " + name;
    }
}

def helloGroovy = new helloGroovy();
helloGroovy.say("Yd");
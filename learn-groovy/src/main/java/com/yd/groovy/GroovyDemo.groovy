package main.java.com.yd.groovy

class groovyDemo {

    static int add(int x, int y) {
        return x + y;
    }
}

def groovyDemo = new groovyDemo();
print(groovyDemo.add(1,2))

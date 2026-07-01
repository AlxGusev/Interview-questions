package easy.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class BeanLifecycleBean {

    private String name;

    public BeanLifecycleBean() {
        this.name = "1";
    }

    @PostConstruct
    public void init() {
        System.out.println("Constructed " + BeanLifecycleBean.class.getName());
        this.name = "constructed";
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroyed " + BeanLifecycleBean.class.getName());
        this.name = "destroyed";
    }

    public String getName() {
        return name;
    }
}

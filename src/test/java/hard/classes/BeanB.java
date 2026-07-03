package hard.classes;

import org.springframework.context.annotation.Lazy;

public class BeanB {
    public final BeanA beanA;

    public BeanB(@Lazy BeanA beanA) {
        this.beanA = beanA;
    }

    public String getName() {return "Bean B";}
}

package builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Course {
    private final String name;
    private final BigDecimal price;
    private final LocalDateTime startDate;

    private Course(CourseBuilder courseBuilder) {
        this.name = courseBuilder.name;
        this.price = courseBuilder.price;
        this.startDate = courseBuilder.startDate;
    }

    public static class CourseBuilder {
        private final String name;
        private BigDecimal price;
        private LocalDateTime startDate;

        public CourseBuilder(String name) {
            this.name = name;
        }

        public CourseBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public CourseBuilder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public static void main(String[] args) {
        Course pythonCourse =
                new CourseBuilder("Python Programming")
                    .price(new BigDecimal("200"))
                    .startDate(LocalDateTime.of(2023, 1, 1, 0, 0))
                    .build();
        System.out.println(pythonCourse.getName());
        System.out.println(pythonCourse.getPrice());
        System.out.println(pythonCourse.getStartDate());
    }
}

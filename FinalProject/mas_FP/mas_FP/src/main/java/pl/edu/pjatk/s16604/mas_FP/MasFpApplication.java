package pl.edu.pjatk.s16604.mas_FP;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MasFpApplication {

    public static void main(String[] args) {
//		SpringApplication.run(MasFpApplication.class, args);
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(MasFpApplication.class).run(args);
        ctx.getBean(DataInitializer.class).initData();
//        SwingUtilities.invokeLater(() -> {
//            ctx.getBean(MainScreenController.class).initGui();
//        });

    }


}

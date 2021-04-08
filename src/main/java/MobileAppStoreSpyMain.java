import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "se.tink.backend.mobile_app_store_spy")
public class MobileAppStoreSpyMain {

    public static void main(String[] args) {
        SpringApplication.run(MobileAppStoreSpyMain.class, args);
    }
}

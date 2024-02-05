package sa.project.css;

import org.springframework.core.io.InputStreamResource;
import org.springframework.kafka.annotation.EnableKafka;
import sa.project.css.configuration.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import sa.project.css.service.CodeSupplierService;
import sa.project.css.utility.WorkDirCreator;

import java.io.FileInputStream;

@SpringBootApplication
@EnableDiscoveryClient
@EnableKafka
public class CodeSupplierServiceApplication implements CommandLineRunner {
	@Autowired
	private WorkDirCreator workDirCreator;

	public static void main(String[] args) {
		SpringApplication.run(CodeSupplierServiceApplication.class, args);
	}
	@Autowired
	private Sender sender;

	@Autowired
	private CodeSupplierService codeSupplierService;

	@Override
	public void run(String... args) throws Exception {
		workDirCreator.createWorkDir();
//		sender.send("CSSTopic", "hello css");
		sender.send("CSS_RESPONSE", codeSupplierService.getRSCode());
		System.out.println(">>>> YESSS");
		System.out.println("--------******** FILE SENT SUCCESSFULLY");
	}
}

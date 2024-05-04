package com.example.TMS;

import com.example.TMS.Entity.Role;
import com.example.TMS.Entity.Users;
import com.example.TMS.Repository.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TmsBackendApplication implements CommandLineRunner {

	@Autowired
	private UserInterface userInterface;
	public static void main(String[] args) {
		SpringApplication.run(TmsBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Users adminAccount = userInterface.findByRole(Role.ADMIN);
		if(null == adminAccount) {
			Users admin = new Users();

			admin.setEmail("admin@gmail.com");
			admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
			admin.setRole(Role.ADMIN);

			userInterface.save(admin);
		}
	}
}

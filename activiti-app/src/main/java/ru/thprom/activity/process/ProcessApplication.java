package ru.thprom.activity.process;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ProcessApplication {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(ProcessApplication.class, args);
	}

	@Bean
	InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {

		return new InitializingBean() {
			public void afterPropertiesSet() throws Exception {

				try {
					List<User> users = identityService.createUserQuery().userId("admin").list();
					if (users == null || users.size() == 0) {
						Group group = identityService.newGroup("user");
						group.setName("users");
						group.setType("security-role");
						identityService.saveGroup(group);

						User admin = identityService.newUser("admin");
						admin.setPassword("admin");
						identityService.saveUser(admin);
					}
				} catch (Exception e) {
					log.error("Error creating user and group ", e);
				}

			}
		};
	}
}

package com.example.supplyChainXProject;

import com.example.supplyChainXProject.dto.user.UserDto;
import com.example.supplyChainXProject.enums.Role;
import com.example.supplyChainXProject.service.user.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SupplyChainXProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplyChainXProjectApplication.class, args);
	}


    @Bean
    CommandLineRunner seedUsers(IUserService userService) {
        return args -> {

            userService.createUser(

                    new UserDto(
                            "Admin",
                            "User",
                            "admin1@example.com",
                            "admin123"
                    ),
                    Role.ADMIN
            );

            userService.createUser(
                    new UserDto(
                            "Gestionnaire",
                            "Approvisionnement",
                            "gestionnaireApprov@example.com",
                            "gest123"
                    ),
                    Role.GESTIONNAIRE_APPROVISIONNEMENT
            );

            userService.createUser(

                    new UserDto(
                            "Responsable",
                            "Achats",
                            "achats@example.com",
                            "achats123"
                    ),
                    Role.RESPONSABLE_ACHATS
            );

            userService.createUser(

                    new UserDto(
                            "Superviseur",
                            "Logistique",
                            "superviseurLog@example.com",
                            "log123"
                    ),
                    Role.SUPERVISEUR_LOGISTIQUE
            );

            userService.createUser(
                    new UserDto(
                            "Chef",
                            "Production",
                            "chefProd@example.com",
                            "prod123"
                    ),
                    Role.CHEF_PRODUCTION
            );

            userService.createUser(

                    new UserDto(
                            "Planificateur",
                            "Production",
                            "planificateur@example.com",
                            "plan123"
                    ),
                    Role.PLANIFICATEUR
            );

            userService.createUser(

                    new UserDto(
                            "Superviseur",
                            "Production",
                            "superviseurProd@example.com",
                            "superProd123"
                    ),
                    Role.SUPERVISEUR_PRODUCTION
            );

            userService.createUser(

                    new UserDto(
                            "Gestionnaire",
                            "Commercial",
                            "gestCommercial@example.com",
                            "com123"
                    ),
                    Role.GESTIONNAIRE_COMMERCIAL
            );

            userService.createUser(

                    new UserDto(
                            "Responsable",
                            "Logistique",
                            "respLogistique@example.com",
                            "respLog123"
                    ),
                    Role.RESPONSABLE_LOGISTIQUE
            );

            userService.createUser(

                    new UserDto(
                            "Superviseur",
                            "Livraisons",
                            "superviseurLiv@example.com",
                            "liv123"
                    ),
                    Role.SUPERVISEUR_LIVRAISONS
            );
        };
    }
}
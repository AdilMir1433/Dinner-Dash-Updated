package com.example.rotiscnz.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
@OpenAPIDefinition(info = @Info(
        contact = @Contact(
                name = "Adil Mir",
                email = "adil.mir@devsinc.com"

        ),
        description = "Restaurant App",
        title = "Roti Scnz - Swagger",
        version = "0.69"

),
servers = @Server(
        description = "DEV",
        url = "http://localhost:8080"
))
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Authentication",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER

)
public class OpenAPIConfig {


}
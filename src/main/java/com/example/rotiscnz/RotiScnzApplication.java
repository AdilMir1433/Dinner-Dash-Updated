package com.example.rotiscnz;


import com.example.rotiscnz.dtos.ItemDTOs.ItemCreateDTO;
import com.example.rotiscnz.dtos.categoryDTOs.CategoryCreateDTO;
import com.example.rotiscnz.dtos.userDTOs.UserSignUpDTO;
import com.example.rotiscnz.entities.CategoryEntity;
import com.example.rotiscnz.repositories.CategoryRepository;
import com.example.rotiscnz.repositories.ItemRepository;
import com.example.rotiscnz.repositories.UserRepository;
import com.example.rotiscnz.serviceinterfaces.CategoryServiceInterface;
import com.example.rotiscnz.serviceinterfaces.ItemServiceInterface;
import com.example.rotiscnz.serviceinterfaces.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class RotiScnzApplication implements CommandLineRunner {
    private final UserServiceInterface userService;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final ItemServiceInterface itemServiceInterface;
    private final CategoryRepository categoryRepository;
    private final CategoryServiceInterface categoryServiceInterface;

    public static void main(String[] args) {
        SpringApplication.run(RotiScnzApplication.class, args);
    }

    @Override
    public void run(String... args){
        if (userRepository.count() == 0) {
            UserSignUpDTO userSignUpDTO = new UserSignUpDTO();
            userSignUpDTO.setPassword("8080");
            userSignUpDTO.setEmail("mir43316@gmail.com");
            userSignUpDTO.setFullName("Admin User");
            userSignUpDTO.setDisplayName("admin69");
            userService.adminSignUp(userSignUpDTO);
            log.info("Admin Created");
        }
        if(itemRepository.count() == 0){
            if(categoryRepository.count() == 0){
                CategoryCreateDTO categoryCreateDTO = new CategoryCreateDTO();
                categoryCreateDTO.setCategoryName("Fast Food");
                categoryCreateDTO.setCategoryPhoto("https://duyt4h9nfnj50.cloudfront.net/new_search_home_eats_icon/FastFood_BrowseHome@3x.png");
                categoryServiceInterface.createCategory(categoryCreateDTO);
            }
            ItemCreateDTO itemCreateDTO = new ItemCreateDTO();
            itemCreateDTO.setDescription("Indulge in the Soft and Rich Steak");
            itemCreateDTO.setPrice(69);
            List<CategoryEntity> categoryEntities = categoryRepository.findAll();
            List<Long> ids = new ArrayList<>();
            categoryEntities.forEach(categoryEntity -> {
                ids.add(categoryEntity.getId());
            });

            itemCreateDTO.setCategoryID(ids);
            itemCreateDTO.setTitle("Steak");
            itemCreateDTO.setItemPhoto("https://images.unsplash.com/photo-1553979459-d2229ba7433b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTh8fGJ1cmdlcnN8ZW58MHx8MHx8&auto=format&fit=crop&w=1400&q=60");
            itemServiceInterface.save(itemCreateDTO);
        }
    }
}

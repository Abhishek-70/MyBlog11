package com.myblog11.myblog11.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.myblog11.myblog11.payload.Postdto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class postServiceImplTest {
    @Autowired
    private postServiceImpl postServiceImpl;

    /**
     * Method under test: {@link postServiceImpl#createPost(Postdto)}
     */
    @Test
    void testCreatePost() {
        // Arrange and Act
        Postdto actualCreatePostResult = postServiceImpl.createPost(
                new Postdto(1L, "Dr", "The characteristics of someone or something", "Not all who wander are lost"));

        // Assert
        assertEquals("Dr", actualCreatePostResult.getTitle());
        assertEquals("Not all who wander are lost", actualCreatePostResult.getContent());
        assertEquals("The characteristics of someone or something", actualCreatePostResult.getDescription());
    }

    /**
     * Method under test: {@link postServiceImpl#createPost(Postdto)}
     */
    @Test
    void testCreatePost2() {
        // Arrange and Act
        Postdto actualCreatePostResult = postServiceImpl.createPost(
                new Postdto(2L, "Dr", "The characteristics of someone or something", "Not all who wander are lost"));

        // Assert
        assertEquals("Dr", actualCreatePostResult.getTitle());
        assertEquals("Not all who wander are lost", actualCreatePostResult.getContent());
        assertEquals("The characteristics of someone or something", actualCreatePostResult.getDescription());
    }

    /**
     * Method under test: {@link postServiceImpl#createPost(Postdto)}
     */
    @Test
    void testCreatePost3() {
        // Arrange and Act
        Postdto actualCreatePostResult = postServiceImpl.createPost(new Postdto());

        // Assert
        assertNull(actualCreatePostResult.getContent());
        assertNull(actualCreatePostResult.getDescription());
        assertNull(actualCreatePostResult.getTitle());
    }
}

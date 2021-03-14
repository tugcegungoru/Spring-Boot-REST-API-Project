package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.domain.CommentDO;
import com.example.demo.domain.BookDO;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetAllBooks() throws Exception {
		mockMvc.perform(get("/api/v1/books")
						.accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(jsonPath("$[*].id").exists())
						.andExpect(jsonPath("$[*].author").exists())
						.andExpect(jsonPath("$[*].name").exists())
						.andExpect(jsonPath("$[*].pageNumber").exists())
						.andExpect(jsonPath("$[*].publisher").exists())
						.andExpect(jsonPath("$[*].summary").exists())
						.andExpect(jsonPath("$[*].barcode").exists());
	}

	@Test
	public void testGetBookById() throws Exception {
		String bookId = "11";

		mockMvc.perform(get("/api/v1/books/{bookId}", bookId)
						.accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.id").exists())
						.andExpect(jsonPath("$[*].author").exists())
						.andExpect(jsonPath("$[*].name").exists())
						.andExpect(jsonPath("$[*].pageNumber").exists())
						.andExpect(jsonPath("$[*].publisher").exists())
						.andExpect(jsonPath("$[*].summary").exists())
						.andExpect(jsonPath("$[*].barcode").exists());

	}

	@Test
	public void testGetBookByAuthor() throws Exception {
		String bookAuthor = "Nazım Hikmet";

		mockMvc.perform(get("/api/v1//book-by-author/{bookAuthor}", bookAuthor)
						.accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.id").exists())
						.andExpect(jsonPath("$[*].author").exists())
						.andExpect(jsonPath("$[*].name").exists())
						.andExpect(jsonPath("$[*].pageNumber").exists())
						.andExpect(jsonPath("$[*].publisher").exists())
						.andExpect(jsonPath("$[*].summary").exists())
						.andExpect(jsonPath("$[*].barcode").exists())
						.andExpect(jsonPath("$[*].comment").exists())
						.andExpect(jsonPath("$.comment.id").exists())
						.andExpect(jsonPath("$.comment.title").exists())
						.andExpect(jsonPath("$.comment.comment").exists());
	}

	@Test
	public void testCreateBook() throws Exception {
		BookDO newBook = new BookDO();
		newBook.setAuthor("Nazım Hikmet");
		newBook.setName("İnce Memed 1");
		newBook.setBarcode(123987);
		newBook.setPageNumber(350);
		newBook.setPublisher("Yapı Kredi Yayınları");
		newBook.setSummary("Çukurova'da geçen herkesin kendini bulabileceği bir eser.");
		CommentDO newComment = new CommentDO();
		newComment.setTitle("Harika");
		newComment.setComment("Herkesin okuması gereken bir kitap!");
		newBook.setComment(newComment);

		mockMvc.perform(post("/api/v1/book")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(newBook)))
						.andExpect(status().isCreated())
						.andExpect(jsonPath("$.id").exists())
						.andExpect(jsonPath("$.barcode").exists())
						.andExpect(jsonPath("$.name").exists())
						.andExpect(jsonPath("$.author").exists())
						.andExpect(jsonPath("$.publisher").exists())
						.andExpect(jsonPath("$.summary").exists())
						.andExpect(jsonPath("$.pageNumber").exists())
						.andExpect(jsonPath("$.comment").exists())
						.andExpect(jsonPath("$.comment.title").exists())
						.andExpect(jsonPath("$.comment.comment").exists())
						.andExpect(jsonPath("$.comment.id").exists());
	}

	@Test
	public void testUpdateBook() throws Exception {
		BookDO newBook = new BookDO();
		newBook.setId(2L);
		newBook.setBarcode(123987);
		newBook.setName("1984");
		newBook.setAuthor("George Orwell");
		newBook.setPageNumber(325);
		newBook.setPublisher("İş Bankası Yayınları");
		newBook.setSummary("Dönemini yansıtan distopik roman");
		CommentDO newComment = new CommentDO();
		newComment.setTitle("Harika");
		newComment.setComment("Herkesin okuması gereken bir kitap!");
		newBook.setComment(newComment);

		mockMvc.perform(put("/api/v1/book")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(newBook)))
						.andExpect(status().isCreated())
						.andExpect(jsonPath("$.barcode").exists())
						.andExpect(jsonPath("$.name").exists())
						.andExpect(jsonPath("$.author").exists())
						.andExpect(jsonPath("$.publisher").exists())
						.andExpect(jsonPath("$.summary").exists())
						.andExpect(jsonPath("$.pageNumber").exists())
						.andExpect(jsonPath("$.comment").exists())
						.andExpect(jsonPath("$.comment.title").exists())
						.andExpect(jsonPath("$.comment.comment").exists())
						.andExpect(jsonPath("$.comment.id").exists());				;
	}

	@Test
	public void testDeleteUser() throws Exception {
		String bookId = "11";

		mockMvc.perform(delete("/api/v1/books/{bookId}", bookId)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk());
	}

	public static String asJsonString(final Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

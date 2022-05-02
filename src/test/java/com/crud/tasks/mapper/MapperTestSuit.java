package com.crud.tasks.mapper;


import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MapperTestSuit {

    @Autowired
    TaskMapper taskMapper;
    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void testMapToTask(){
        //Given
        TaskDto givenTaskDto = new TaskDto(1L, "title-test", "content-test");

        //When
        Task resultTask = taskMapper.mapToTask(givenTaskDto);

        //Then
        assertEquals(1L, resultTask.getId());
        assertEquals("title-test", resultTask.getTitle());
        assertEquals("content-test", resultTask.getContent());
    }

    @Test
    public void testMapToTaskDto(){
        //Given
        Task givenTask = new Task(4L, "title", "content");

        //When
        TaskDto resultTaskDto = taskMapper.mapToTaskDto(givenTask);

        //Then
        assertEquals(4L, resultTaskDto.getId());
        assertEquals("title", resultTaskDto.getTitle());
        assertEquals("content", resultTaskDto.getContent());
    }

    @Test
    public void testMapToBoards(){
        //Given
        List<TrelloBoardDto> givenListTrelloBoardDto = new ArrayList<>();
        for (int i=0; i <10; i++ ){
            List<TrelloListDto> givenListTrelloListDto = new ArrayList<>();
            givenListTrelloBoardDto.add(new TrelloBoardDto("id" + i, "name" + i, givenListTrelloListDto));
            for (int j=0; j<10; j++) {
                givenListTrelloListDto.add(new TrelloListDto("name" + j, "id" + j, false));
            }
        }

        //When
        List<TrelloBoard> resultListTrelloBoard = trelloMapper.mapToBoards(givenListTrelloBoardDto);

        //Then
        assertEquals(10, resultListTrelloBoard.size());
        assertEquals(10, resultListTrelloBoard.get(5).getLists().size());

    }

    @Test
    public void testMapToBoardsDto(){
        //Given
        List<TrelloBoard> givenListTrelloBoard = new ArrayList<>();
        for (int i=0; i <10; i++ ){
            List<TrelloList> givenListTrelloList = new ArrayList<>();
            givenListTrelloBoard.add(new TrelloBoard("id" + i, "name" + i, givenListTrelloList));
            for (int j=0; j<10; j++) {
                givenListTrelloList.add(new TrelloList("name" + j, "id" + j, false));
            }
        }
        //When
        List<TrelloBoardDto> resultListTrelloBoardDto = trelloMapper.mapToBoardsDto(givenListTrelloBoard);

        //Then
        assertEquals(10, resultListTrelloBoardDto.size());
        assertEquals(10, resultListTrelloBoardDto.get(5).getLists().size());
    }

    @Test
    public void testMapToCardDto(){
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "description", "pos", "listId");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("name", trelloCardDto.getName());
        assertEquals("listId", trelloCardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "listId");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("name", trelloCard.getName());
        assertEquals("listId", trelloCard.getListId());
    }
}

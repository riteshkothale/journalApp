package com.journal.journalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
@Data
public class JournalEntry {

      @Id
      private ObjectId id;

      @NonNull
      private String title;

      private String content;

      private  LocalDateTime localDateTime;



}

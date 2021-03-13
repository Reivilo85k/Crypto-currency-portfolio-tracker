package com.example.tracker.controllers;

import com.example.tracker.services.EntryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/entry")
@AllArgsConstructor
public class EntryController {

    private final EntryService entryService;

}

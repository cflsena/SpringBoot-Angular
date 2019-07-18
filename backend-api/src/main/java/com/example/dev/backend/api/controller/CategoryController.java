package com.example.dev.backend.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dev.backend.api.constraint.ApiMappingContraint;

@RestController
@RequestMapping(value = ApiMappingContraint.BASE_URL)
public class CategoryController {

}

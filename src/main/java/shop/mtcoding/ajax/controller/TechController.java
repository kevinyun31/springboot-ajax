package shop.mtcoding.ajax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.ajax.dto.TechResponse;
import shop.mtcoding.ajax.model.Category;
import shop.mtcoding.ajax.model.CategoryRepository;
import shop.mtcoding.ajax.model.Tech;
import shop.mtcoding.ajax.model.TechRepository;

@Controller
public class TechController {

    @Autowired
    private TechRepository techRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/v1/test/tech")
    public @ResponseBody TechResponse.MainDTO techV1() {
        List<Category> categoryList = categoryRepository.findAll();
        List<Tech> techList = techRepository.findByCategoryId(1);
        System.out.println("=================================");
        TechResponse.MainDTO mainDTO = new TechResponse.MainDTO(categoryList, techList);
        return mainDTO; // messageconverter 발동 - json 변환
    }

    @GetMapping("/v2/test/tech")
    public @ResponseBody List<Tech> techV2() {
        List<Tech> techList = techRepository.findByCategoryId(1);
        return techList; // messageconverter 발동 - json 변환
    }

    // 1. 빈껍데기 디자인을 준다 (데이터 없음)
    @GetMapping("/tech")
    public String tech() {
        return "main";
    }

    @GetMapping("/api/category")
    public @ResponseBody List<Category> categoryApi() {
        return categoryRepository.findAll();
    }

    @GetMapping("/api/tech")
    public @ResponseBody List<Tech> techApi(
            @RequestParam(defaultValue = "1") Integer categoryId) {
        return techRepository.findByCategoryId(categoryId);
    }
}
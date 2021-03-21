package com.kozhievinal;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;


@Controller
@RequestMapping("/wordanalyzer")
public class Main {

    @GetMapping("/analyze")
    public void foo(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String word = req.getParameter("word");
        PrintWriter pw = resp.getWriter();

        Letter letter = analyze(word);

        StringWriter sw = new StringWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(sw, letter);

        pw.println(sw);

    }


    public static Letter analyze(String word) {

        List<Character> list = new ArrayList<>();

        String reverseWord = new StringBuilder(word).reverse().toString();
        for (char c : reverseWord.toCharArray()) {
            list.add(c);
        }

        Map<Character, Integer> map = new LinkedHashMap<>();
        list.forEach(character -> {
            map.computeIfPresent(character, (letter, count) -> count+1);
            map.putIfAbsent(character, 1);
        });

        Map.Entry<Character, Integer> entry = map.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()).get();

        return new Letter(entry.getKey(), entry.getValue());
    }
}

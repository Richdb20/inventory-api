
package com.example.inventory_api.controller;

import com.example.inventory_api.repository.SwitchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;

@Controller
public class SwitchPageController {

    private final SwitchRepository switchRepository;

    public SwitchPageController(SwitchRepository switchRepository) {
        this.switchRepository = switchRepository;
    }

    @GetMapping("/inventory-api")
    public String inventoryPage(
            Model model,
            Authentication authentication) {

        model.addAttribute("switches", switchRepository.findAll());

        model.addAttribute(
                "username",
                authentication.getName()
        );

        return "inventory";
    }
}

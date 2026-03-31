package com.itmanager.controller;

import com.itmanager.dto.AssetFormDTO;
import com.itmanager.model.Asset;
import com.itmanager.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AssetController {

    @Autowired
    private AssetRepository repository;

    @GetMapping("/")
    public String list(Model model, @RequestParam(required = false) String keyword) {

        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("assets", repository.findByNameContainingIgnoreCaseOrSerialNumberContainingIgnoreCaseOrderByIdAsc(keyword, keyword));
        } else {
            model.addAttribute("assets", repository.findAllByOrderByIdAsc());
        }

        model.addAttribute("asset", new AssetFormDTO());

        model.addAttribute("totalAssets", repository.count());
        model.addAttribute("available", repository.countByStatus("AVAILABLE"));
        model.addAttribute("inUse", repository.countByStatus("IN_USE"));
        model.addAttribute("inMaintenance", repository.countByStatus("MAINTENANCE"));

        return "index";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute AssetFormDTO assetFormDTO, RedirectAttributes redirectAttributes) {
        try {
            Asset asset;

            if (assetFormDTO.getId() != null) {
                asset = repository.findById(assetFormDTO.getId()).orElseThrow(() -> new IllegalArgumentException("Ativo não encontrado"));
            } else {
                asset = new Asset();
            }

            asset.setName(assetFormDTO.getName());
            asset.setSerialNumber(assetFormDTO.getSerialNumber());
            asset.setCategory(assetFormDTO.getCategory());
            asset.setStatus(assetFormDTO.getStatus());
            asset.setNotes(assetFormDTO.getNotes());

            repository.save(asset);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Equipamento salvo com sucesso!");

        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro: O Número de Série informado já está cadastrado em outro equipamento!");
        }
        return "redirect:/";
    }

    @PostMapping("/status/{id}")
    public String changeStatus(@PathVariable Long id) {
        Asset asset = repository.findById(id).orElseThrow();

        if (asset.getStatus().equals("AVAILABLE")) {
            asset.setStatus("IN_USE");
        } else if (asset.getStatus().equals("IN_USE")) {
            asset.setStatus("MAINTENANCE");
        } else {
            asset.setStatus("AVAILABLE");
        }

        repository.save(asset);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        repository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Equipamento excluído com sucesso!");
        return "redirect:/";
    }
}
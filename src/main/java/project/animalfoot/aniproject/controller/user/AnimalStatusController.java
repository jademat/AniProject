package project.animalfoot.aniproject.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.animalfoot.aniproject.domain.user.*;
import project.animalfoot.aniproject.service.user.AnimalService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class AnimalStatusController {

    @Autowired
    private AnimalService animalService;

    // 지역구별 유기동물 현황 페이지
    @GetMapping("/aboutus/burget")
    public String burget(Model model) {
        // 지역구별 유기동물 현황
        List<AnimalStatusDTO> animalStatusByDistrict = animalService.getAnimalStatusByDistrict();
        if (animalStatusByDistrict == null) {
            animalStatusByDistrict = new ArrayList<>();
        }

        // 지역구별 파양된 강아지, 고양이 현황
        List<ReturnedDTO> returnedDogsAndCats = animalService.getReturnedDogsAndCats();
        if (returnedDogsAndCats == null) {
            returnedDogsAndCats = new ArrayList<>();
        }

        // 지역구별 입양된 강아지, 고양이 현황
        List<AdoptedDTO> adoptedDogsAndCats = animalService.getAdoptedDogsAndCats();
        if (adoptedDogsAndCats == null) {
            adoptedDogsAndCats = new ArrayList<>();
        }

        // 지역구별 안락사된 강아지, 고양이 현황
        List<EuthanizedDTO> euthanizedDogsAndCats = animalService.getEuthanizedDogsAndCats();
        if (euthanizedDogsAndCats == null) {
            euthanizedDogsAndCats = new ArrayList<>();
        }

        // 지역구별 이송된 강아지, 고양이 현황
        List<TransferredDTO> transferredDogsAndCats = animalService.getTransferredDogsAndCats();
        if (transferredDogsAndCats == null) {
            transferredDogsAndCats = new ArrayList<>();
        }

        // 지역구 목록
        List<String> districts = new ArrayList<>();
        for (AnimalStatusDTO status : animalStatusByDistrict) {
            districts.add(status.getDistrict());
        }

        // 각 동물 상태 데이터 (강아지, 고양이)
        List<Integer> totalDogs = new ArrayList<>();
        List<Integer> totalCats = new ArrayList<>();
        List<Integer> returnedDogs = new ArrayList<>();
        List<Integer> returnedCats = new ArrayList<>();
        List<Integer> adoptedDogs = new ArrayList<>();
        List<Integer> adoptedCats = new ArrayList<>();
        List<Integer> euthanizedDogs = new ArrayList<>();
        List<Integer> euthanizedCats = new ArrayList<>();
        List<Integer> transferredDogs = new ArrayList<>();
        List<Integer> transferredCats = new ArrayList<>();

        // 동물 상태 데이터 추가
        for (AnimalStatusDTO status : animalStatusByDistrict) {
            totalDogs.add(status.getTotal_dogs());
            totalCats.add(status.getTotal_cats());
        }

        for (ReturnedDTO returned : returnedDogsAndCats) {
            returnedDogs.add(returned.getReturnedDogs());
            returnedCats.add(returned.getReturnedCats());
        }

        for (AdoptedDTO adopted : adoptedDogsAndCats) {
            adoptedDogs.add(adopted.getAdoptedDogs());
            adoptedCats.add(adopted.getAdoptedCats());
        }

        for (EuthanizedDTO euthanized : euthanizedDogsAndCats) {
            euthanizedDogs.add(euthanized.getEuthanizedDogs());
            euthanizedCats.add(euthanized.getEuthanizedCats());
        }

        for (TransferredDTO transferred : transferredDogsAndCats) {
            transferredDogs.add(transferred.getTransferredDogs());
            transferredCats.add(transferred.getTransferredCats());
        }

        // 그래프에 필요한 데이터 추가
        model.addAttribute("animalStatusByDistrict", animalStatusByDistrict);
        model.addAttribute("returnedDogsAndCats", returnedDogsAndCats);
        model.addAttribute("adoptedDogsAndCats", adoptedDogsAndCats);
        model.addAttribute("euthanizedDogsAndCats", euthanizedDogsAndCats);
        model.addAttribute("transferredDogsAndCats", transferredDogsAndCats);
        model.addAttribute("districts", districts);
        model.addAttribute("totalDogs", totalDogs);
        model.addAttribute("totalCats", totalCats);
        model.addAttribute("returnedDogs", returnedDogs);
        model.addAttribute("returnedCats", returnedCats);
        model.addAttribute("adoptedDogs", adoptedDogs);
        model.addAttribute("adoptedCats", adoptedCats);
        model.addAttribute("euthanizedDogs", euthanizedDogs);
        model.addAttribute("euthanizedCats", euthanizedCats);
        model.addAttribute("transferredDogs", transferredDogs);
        model.addAttribute("transferredCats", transferredCats);

        return "views/user/aboutus/burget";
    }

    @GetMapping("/aboutus/hello")
    public String hello() {
        return "views/user/aboutus/hello";
    }

    @GetMapping("/aboutus/location")
    public String location() {
        return "views/user/aboutus/location";
    }

    @GetMapping("/aboutus/error404")
    public String error() {
        return "views/user/aboutus/error404";
    }
}

package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if(count==0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);


        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Mikael");
        owner1.setLastName("De Win");
        owner1.setAdress("veldenstraat 71");
        owner1.setCity("Mechelen");
        owner1.setTelephone("015000000");

        Pet mikaPet = new Pet();
        mikaPet.setPetType(savedCatPetType);
        mikaPet.setName("kerel");
        mikaPet.setBirthDate(LocalDate.of(2019,3,1));
        mikaPet.setOwner(owner1);
        owner1.getPets().add(mikaPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("karen");
        owner2.setLastName("Van der Zee");
        owner2.setAdress("veldenstraat 71");
        owner2.setCity("Mechelen");
        owner2.setTelephone("015000000");

        Pet karenPet = new Pet();
        karenPet.setBirthDate(LocalDate.now());
        karenPet.setPetType(savedDogPetType);
        karenPet.setName("Malinois");
        karenPet.setOwner(owner2);
        owner2.getPets().add(karenPet);

        ownerService.save(owner2);

        System.out.println("Loaded owners......");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Porter");
        vet1.getSpecialties().add(savedSurgery);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Louise");
        vet2.setLastName("porter");
        vet2.getSpecialties().add(savedRadiology);
        vet2.getSpecialties().add(savedDentistry);

        vetService.save(vet2);

        System.out.println("Loaded vets........");
    }
}

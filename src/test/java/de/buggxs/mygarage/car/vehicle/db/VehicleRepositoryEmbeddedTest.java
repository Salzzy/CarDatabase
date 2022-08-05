package de.buggxs.mygarage.car.vehicle.db;

import de.buggxs.mygarage.car.brand.Brand;
import de.buggxs.mygarage.car.brand.ModelSeries;
import de.buggxs.mygarage.car.brand.ModelSeriesGeneration;
import de.buggxs.mygarage.car.vehicle.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class VehicleRepositoryEmbeddedTest {

    final Brand[] CONST_BRANDS = {
            new Brand(1L, "VW", ""),
            new Brand(3L, "Mercedes", ""),
            new Brand(2L, "Opel", ""),
    };

    final ModelSeries[] CONST_MODEL_SERIES = {
            new ModelSeries(1L, "Arteon", "", CONST_BRANDS[0]),
            new ModelSeries(2L, "Golf", "", CONST_BRANDS[0]),
            new ModelSeries(9L, "Tiguan", "", CONST_BRANDS[0]),
            new ModelSeries(119L, "SL-Klasse", "", CONST_BRANDS[1]),
    };

    final ModelSeriesGeneration[] CONST_MODEL_SERIES_GENERATION = {
            new ModelSeriesGeneration(2L, "Arteon 1.Generation", "", CONST_MODEL_SERIES[0], null),
            new ModelSeriesGeneration(3L, "Golf VIII", "", CONST_MODEL_SERIES[1], null),
            new ModelSeriesGeneration(49L, "Tiguan I", "", CONST_MODEL_SERIES[2], null),
            new ModelSeriesGeneration(371L, "SL-Klasse 113", "", CONST_MODEL_SERIES[3], null),
    };

    final Vehicle[] CONST_VEHICLES = {
            new Vehicle(1L, "Arteon  (3H)", "", "VW Arteon 1.4 eHybrid OPF Elegance DSG (ab 11/20)", "", 2L, CONST_MODEL_SERIES_GENERATION[0], null, null),
            new Vehicle(2L, "Golf  (CD)", "", "VW Golf 1.0 TSI ACTIVE (01/21 - 08/21)", "", 3L, CONST_MODEL_SERIES_GENERATION[1], null, null),
            new Vehicle(9991L, "Tiguan  (5N)", "", "VW Tiguan 1.4 TSI BMT Cityscape (05/15 - 04/16)", "", 49L, CONST_MODEL_SERIES_GENERATION[2], null, null),
            new Vehicle(39988L, "SL-Klasse  (W113)", "", "Mercedes 230 SL Automatik (07/63 - 01/67)", "", 371L, CONST_MODEL_SERIES_GENERATION[3], null, null),
    };

    @Autowired
    private VehicleRepository vehicleRepository;

    @BeforeEach
    void setUp() {
        vehicleRepository.saveAll(Arrays.asList(CONST_VEHICLES));
    }

    @Test
    void findAllVehicles_Test() {
        Page<Vehicle> vehiclesPage = vehicleRepository.findAll(Pageable.ofSize(20));
        String testVehicleName = vehiclesPage.getContent().get(0).getName();
        assertThat(vehiclesPage)
                .isNotEmpty()
                .hasSize(4);
        assertThat(testVehicleName).isEqualTo(CONST_VEHICLES[0].getName());
    }

    @Test
    void searchByMakerNameAndDate() {
        Page<Vehicle> vehiclePage = vehicleRepository.getAllVehiclesByModelAndMaker("vw", null, null, Pageable.ofSize(20));
    }

}

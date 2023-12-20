package pkg.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("factory")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FactoryEmission {
    private String area_nm;
    private String fact_manage_nm;
    private String stack_code;
    private String mesure_dt;
    private double lat;
    private double lon;

    private String co_exhst_perm_stdr_value;
    private String co_mesure_value;

    private String hcl_exhst_perm_stdr_value;
    private String hcl_mesure_value;

    private String hf_exhst_perm_stdr_value;
    private String hf_mesure_value;

    private String nh3_exhst_perm_stdr_value;
    private String nh3_mesure_value;

    private String nox_exhst_perm_stdr_value;
    private String nox_mesure_value;

    private String sox_exhst_perm_stdr_value;
    private String sox_mesure_value;

    private String tsp_exhst_perm_stdr_value;
    private String tsp_mesure_value;

}

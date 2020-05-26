package com.eacj.sercapi.domain.scripts.location;

import com.eacj.sercapi.domain.model.Cidade;
import com.eacj.sercapi.domain.model.Estado;
import java.util.Arrays;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CidadeEstadoLoader {

    public static void main(String[] args) {
        new CidadeEstadoLoader().loadData();
                
    }
    
    public void loadData() {
        
        RestTemplate rt = new RestTemplate();
        String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";

        ResponseEntity<EstadoTemp[]> resp = rt.getForEntity(url, EstadoTemp[].class);
        
        for(EstadoTemp et : Arrays.asList(resp.getBody())){
            
            System.out.println(et.getNome() + " - " + et.getSigla());
            
            Estado e = new Estado();
            e.setNome(e.getNome());
            e.setUf(et.getSigla());
            
            //save command
            
            url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/"+e.getUf()+"/municipios";
            ResponseEntity<Municipios[]> resp2 = rt.getForEntity(url, Municipios[].class);
            
            System.out.println("Cidades ------------------------------");
            
            for(Municipios m : Arrays.asList(resp2.getBody())){
                
                System.out.println(m.getNome());
                
                Cidade c = new Cidade();
                c.setEstado(e);
                c.setNome(m.getNome());
                
                //save command
                
            }
            
        }
            
    }
    

}

package PDF;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tc3.parquimetro.dominio.condutor.entidade.Condutor;
import com.tc3.parquimetro.dominio.pagamento.entidade.Pagamento;
import com.tc3.parquimetro.dominio.veiculo.entidade.Veiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class Recibo {

    public static void main(String[] args) {
       
        float larguraPagina = 300;
        float alturaPagina = 500;

        private Conductor condutor;
        
        private Veiculo veiculo;
        
        private string placa;
       
        Rectangle tamanhoPagina = new Rectangle(larguraPagina, alturaPagina);

        Document documento = new Document(tamanhoPagina);
        
        String caminhoDaPasta = "C:\\Users\\Samsung\\Documents\\Recibo";
        
        File pasta = new File(caminhoDaPasta);
        
        if (!pasta.exists()) {
         
            boolean criada = pasta.mkdirs(); 

            if (criada) 
            {
                System.out.println("Pasta criada com sucesso!");
            } 
            
            else 
            {
                System.out.println("Falha ao criar a pasta.");
            }
        } 
        
        else 
        {
            System.out.println("A pasta já existe.");
        }

        try {
            
            PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\Samsung\\Documents\\Recibo\\vale.pdf"));

            documento.open();

            
            adicionarConteudoRecibo(documento, condutor, placa, veiculo, "Produto/Serviço", 100.00);

            documento.close();
            System.out.println("Recibo criado com sucesso!");
        } 
        catch (DocumentException | FileNotFoundException e) 
        {
            e.printStackTrace();
        }
    }

    private static void adicionarConteudoRecibo(Document documento, String nome, String placa, String veiculo, String produto, double valor) throws DocumentException {
       
        documento.add(new Paragraph("---------------Recibo------------------"));
        documento.add(new Paragraph("                                       "));
        documento.add(new Paragraph("           ESTACIONAMENTO           "));
        documento.add(new Paragraph("                                       "));
        documento.add(new Paragraph("---------------------------------------"));
        documento.add(new Paragraph("Nome do Cliente: " + nome));
        documento.add(new Paragraph("Placa: " + placa));
        documento.add(new Paragraph("Veiculo: " + veiculo));
        documento.add(new Paragraph("Data: " + "28/10/2023"));
        documento.add(new Paragraph("Hora: " + "12:00:00"));
        documento.add(new Paragraph("---------------------------------------"));
        documento.add(new Paragraph("Descrição do Produto/Serviço: " + produto));
        documento.add(new Paragraph("Valor: R$" + valor));
        documento.add(new Paragraph("---------------------------------------"));
        documento.add(new Paragraph("Obrigado e volte sempre!!"));
    }
}

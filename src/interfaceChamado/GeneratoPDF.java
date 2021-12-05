package interfaceChamado;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import model.Chamado;

class GeneratorPDF {
  public static final String RESOURCE = "C:\\Users\\User\\Documents\\Projeto Integrador v1\\src\\App\\carbon-footprint.png";

    public GeneratorPDF(float pegada, Chamado chamado, float km){
    // criação do objeto documento

      Document document = new Document();
      LocalDate myDate = LocalDate.now();

      try {
        PdfWriter.getInstance(document, new FileOutputStream("C:\\Relatório_Pegada_De_Carbono_Nº_" + chamado.getId() + ".pdf"));
        document.open();
        document.add(Image.getInstance(String.format(RESOURCE)));
        document.add(new Paragraph(30,"Relatório de pegada de carbono e impacto gerado ao ecossistema."));
        document.addTitle("Relatório refente ao Chamado de Nº de identifação: " + chamado.getId());
        // adicionando um parágrafo no documento
        System.out.println(chamado.getTrajetoFim());
        document.add(new Paragraph(70," "));
        document.add(new Paragraph("Chamado de Nº " + chamado.getId() +
            " com destino final à " + chamado.getTrajetoFim() +
            " executado por " + chamado.getFuncionario().getNome() +
            " com  o carro de Nº identificação: " + chamado.getVeiculo().getId() +
            ", modelo " + chamado.getVeiculo().getModelo() + "." +
            " Ao final sua kilometragem total foi de " + km + " KM" +
            " assim sendo emitido um total de " + String.format("%.02f" , pegada)  + " kg/L de CO2 na atmosfera." +
            " Tornando nescessário para compensação de CO2 em até um ano á plantação de " + String.format("%.01f" ,(pegada/15.6))  + " árvores " +
            " para mais informações sobre compensação de CO2 acesse: \nhttps://www.sustainablecarbon.com/blog/tudo-o-que-voce-precisa-saber-sobre-compensacao-de-carbono/" ));

        document.add(new Paragraph(450,"Este documento foi gerado em " + myDate + " por " + chamado.getFuncionario().getNome()));

      }
      catch(DocumentException | IOException de) {
        System.err.println(de.getMessage());
      }
      document.close();
    }

  }

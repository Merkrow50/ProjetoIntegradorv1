package interfaceChamado;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import model.Chamado;

class GeneratorPDF {
  public static final String RESOURCE = "C:\\Users\\User\\Documents\\Projeto Integrador v1\\src\\App\\carbon-footprint.png";

    public GeneratorPDF(float pegada, Chamado chamado){
    // criação do objeto documento

      Document document = new Document();
      try {

        PdfWriter.getInstance(document, new FileOutputStream("C:\\Relatório_Pegada_De_Carbono_Nº_" + chamado.getId() + ".pdf"));
        document.open();
        document.add(Image.getInstance(String.format(RESOURCE)));
        document.addTitle("Relatório refente ao Chamado de Nº de identifação: " + chamado.getId());
        // adicionando um parágrafo no documento
        document.add(new Paragraph("Sua Pegada de Carbono Total Foi de: " + String.format("%.02f" , pegada)  + " KG de CO2 emitido por litro!"));

      }
      catch(DocumentException | IOException de) {
        System.err.println(de.getMessage());
      }
      document.close();
    }

  }

package conversor;

public class ConversorLongitud {
	public static double convertirLongitudes(String siglasDe, String siglasA, double valor){
        double montoConvertido = 0.0;
        String siglasConversion = siglasDe + " - " + siglasA;
        
        switch (siglasConversion) {
            case "m - km" -> montoConvertido = valor / 1000;
                
            case "m - yd" -> montoConvertido = valor * 1.094;

            case "km - m" -> montoConvertido = valor * 1000;

            case "km - yd" -> montoConvertido = valor * 1093.61;

            case "yd - m" -> montoConvertido = valor / 1.094;

            case "yd - km" -> montoConvertido = valor / 1093.61;
        }
        return montoConvertido; 
    }
}

package conversor;

public class ConversorTemperatura {
	public static double convertirTemperaturas(String siglasDe, String siglasA, double valor){
        double montoConvertido = 0.0;
        String siglasConversion = siglasDe + " - " + siglasA;
        
        switch (siglasConversion) {
            case "C - K" -> montoConvertido = valor + 273.15;
                
            case "C - F" -> montoConvertido = (valor * 9/5) + 32;

            case "K - C" -> montoConvertido = valor - 273.15;

            case "K - F" -> montoConvertido = (valor - 273.15) * 9/5 +32;

            case "F - C" -> montoConvertido = (valor - 32) * 5/9;

            case "F - K" -> montoConvertido = (valor - 32) * 5/9 + 273.15;
        }
        return montoConvertido; 
    }
}

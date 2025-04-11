package main.java.com.bytebank.util;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatadorUtil {
    
    private static final Locale LOCALE_BR = new Locale("pt", "BR");
    private static final NumberFormat FORMATADOR_MOEDA = NumberFormat.getCurrencyInstance(LOCALE_BR);
    private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    private FormatadorUtil() {
        // Construtor privado para evitar instanciação
    }
    
    public static String formatarMoeda(double valor) {
        return FORMATADOR_MOEDA.format(valor);
    }
    
    public static String formatarData(LocalDateTime data) {
        return FORMATADOR_DATA.format(data);
    }
    
    public static String formatarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return cpf;
        }
        
        return cpf.substring(0, 3) + "." + 
               cpf.substring(3, 6) + "." + 
               cpf.substring(6, 9) + "-" + 
               cpf.substring(9);
    }
}
package carpetabot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class tareabot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "ChiltepeBot";
    }

    @Override
    public String getBotToken() {
        return "7322241803:AAFfiMfbIYkQok2KPRscso95jGlbz_gALhs";
    }
    private void sendMessageToCompañeros(String text) {
        for (Long chatId : compañerosIds) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId.toString());
            message.setText(text);

            try {
                execute(message); // Llama a la API de Telegram para enviar el mensaje
            } catch (TelegramApiException e) {
                e.printStackTrace(); // Maneja cualquier excepción de la API
            }
        }
        }
    private final List<Long> compañerosIds = List.of(
            6709392176L,  // Reemplaza estos IDs con los verdaderos
            6922425377L,
            1455734475L,
            6710213754L
    );

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String nombre = update.getMessage().getFrom().getFirstName();
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            if (message_text.equalsIgnoreCase("/info")) {
                sendText(chat_id, "Nombre: Alan Josué Luna Cardona\nCarnet: 0905-23-1346\nSemestre: IV");
            } else if (message_text.equalsIgnoreCase("/progra")) {
                sendText(chat_id, "La clase de programación es una de las clases más esenciales de la carrera de Ingenería en Sistemas. Además de mi parte la siento bastante entretenida y a veces la comparo con armar un set de lego  ");
            } else if (message_text.equalsIgnoreCase("/hola")) {
                String formattedDate = new SimpleDateFormat("EEEE d 'de' MMMM").format(new Date());
                sendText(chat_id, "Hola, " + nombre + ", hoy es " + formattedDate);
            } else if (message_text.startsWith("/cambio")) {
                try {
                    String[] parts = message_text.split(" ");
                    double euros = Double.parseDouble(parts[1]);
                    double tipoDeCambio = 8.53; // Puedes actualizar esto con el tipo de cambio actual.
                    double quetzales = euros * tipoDeCambio;
                    sendText(chat_id, String.format("Son %.2f quetzales.", quetzales));
                } catch (NumberFormatException e) {
                    sendText(chat_id, "Por favor, ingresa un valor numérico válido después de /cambio.");
                }
            } else if (message_text.startsWith("/grupal")) {
                String message = message_text; // Obtiene el mensaje después del comando
                sendMessageToCompañeros(message);
            } else {
                sendText(chat_id, "No entiendo lo que quieres decir.");
            }
        }
    }

    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) // A quién enviamos un mensaje
                .text(what)             // Contenido del mensaje
                .build();
        try {
            execute(sm);               // Enviar el mensaje
        } catch (TelegramApiException e) {
            e.printStackTrace();       // Imprimir el error en caso de que ocurra
        }

    }


        }



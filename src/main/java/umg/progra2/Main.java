package umg.progra2;
import carpetabot.tareabot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args){
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            tareabot bot = new tareabot();
            botsApi.registerBot(bot);
            System.out.println("El Bot esta funcionando...................");
        }
        catch (TelegramApiException ex){
            System.out.println("error"+ex.getMessage());
        }
    }
}
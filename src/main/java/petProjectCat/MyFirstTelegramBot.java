package petProjectCat;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static petProjectCat.TelegramBotContent.*;
import static petProjectCat.TelegramBotUtils.*;

public class MyFirstTelegramBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "JeKoIrD_Bot";
    }


    @Override
    public String getBotToken() {
        return "7210943026:AAG8RvgH0m4fVIUi67TRByGtHZ8OdFJAL2I";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = getChatId(update);

        if (update.hasMessage() && update.getMessage().getText().equals("/start")) {

            sendMessage(chatId, 0, "step_1_pic", STEP_1_TEXT,
                    Map.of("Hacking a fridge", "step_1_btn"));

        }
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("step_1_btn") && getGlories(chatId) == 0) {

                sendMessage(chatId, 20, "step_2_pic", STEP_2_TEXT,
                        Map.of("Take a sausage! +20 glory", "step_2_btn",
                                "Take a fish! +20 glory", "step_2_btn",
                                "Throw a jar of cucumbers! +20 glory", "step_2_btn"));

            } else if (update.getCallbackQuery().getData().equals("step_2_btn") && getGlories(chatId) == 20) {
                sendMessage(chatId, 0, "step_3_pic", STEP_3_TEXT,
                        Map.of(
                                "Hacking a robot vacuum cleaner", "step_3_btn"
                        ));
            } else if (update.getCallbackQuery().getData().equals("step_3_btn") && getGlories(chatId) == 20) {

                sendMessage(chatId, 30, "step_4_pic", STEP_4_TEXT,
                        Map.of(
                                "Send a robot vacuum cleaner to pick up food! +30 glory", "step_4_btn",
                                "Take a ride on a robot vacuum cleaner! +30 glory", "step_4_btn",
                                "Run away from the robot vacuum cleaner! +30 glory", "step_4_btn"
                        ));

            } else if (update.getCallbackQuery().getData().equals("step_4_btn") && getGlories(chatId) == 50) {
                sendMessage(chatId, 0, "step_5_pic", STEP_5_TEXT,
                        Map.of(
                                "Put on your GoPro and switch on!", "step_5_btn"
                        ));

            } else if (update.getCallbackQuery().getData().equals("step_5_btn") && getGlories(chatId) == 50) {
                sendMessage(chatId, 40, "step_6_pic", STEP_6_TEXT,
                        Map.of(
                                "Running on the roofs, filming on GoPro! +40 glory", "step_6_btn",
                                "Ambush other cats with your GoPro! +40 glory", "step_6_btn",
                                "Ambush dogs with GoPro! +40 glory", "step_6_btn"
                        ));

            } else if (update.getCallbackQuery().getData().equals("step_6_btn") && getGlories(chatId) == 90) {

                sendMessage(chatId, 0, "step_7_pic", STEP_7_TEXT,
                        Map.of(
                                "Hacking a password", "step_7_btn"
                        ));

            } else if (update.getCallbackQuery().getData().equals("step_7_btn") && getGlories(chatId) == 90) {
                sendMessage(chatId, 50, "step_8_pic", STEP_8_TEXT,
                        Map.of(
                                "Go out into the yard", "step_8_btn"
                        ));

            } else if (update.getCallbackQuery().getData().equals("step_8_btn") && getGlories(chatId) == 140) {

                sendMessage(chatId, 0, "final_pic", FINAL_TEXT,
                        Map.of());
            }
        }
    }

    private void sendMessage(Long chatId, int glory, String pickName, String text, Map<String, String> buttons) {
        addGlories(chatId, glory);
        SendPhoto photoMessage = createPhotoMessage(chatId, pickName);
        executeAsync(photoMessage);

        SendMessage message = createMessage(chatId, text, buttons);
        sendApiMethodAsync(message);

    }

    private String userName(Update update) { /** Get user name */
        User user = update.getMessage().getFrom();
        var username = user.getUserName();
        return username;
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}
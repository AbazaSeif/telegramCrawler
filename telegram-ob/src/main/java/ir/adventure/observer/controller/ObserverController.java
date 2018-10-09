package ir.adventure.observer.controller;

import ir.adventure.observer.dto.FinalizeReport;
import ir.adventure.observer.dto.JoinResponse;
import ir.adventure.observer.exception.BadRequestException;
import ir.adventure.observer.service.AccessService;
import ir.adventure.observer.service.ChannelService;
import ir.adventure.observer.service.TelegramClients;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jalil on 11/7/2017.
 */
@RestController
@RequestMapping("observer")
public class ObserverController {
    @Autowired
    TelegramClients telegramClients;
    @Autowired
    ChannelService channelService;

    @Autowired
    AccessService accessService;

    @RequestMapping("finalize/{adId}")
    public FinalizeReport getAdHistory(@PathVariable Long adId, String accessHash) {
        if (!accessService.checkAccessHash(accessHash))
            throw new BadRequestException("invalid Exception");
        Long messages = Long.valueOf(channelService.getAdMessageCount(adId));
        Long adViews = telegramClients.getAdViews(adId);
        Long deleteAdMessage = telegramClients.deleteAdMessage(adId);

        return new FinalizeReport(messages, adViews, deleteAdMessage, true);

    }
    @RequestMapping("join/{username}")
    public JoinResponse joinChannel(String accessHash, @PathVariable String username){
        if (!accessService.checkAccessHash(accessHash))
            throw new BadRequestException("invalid Exception");
        return telegramClients.joinChannel(username);
    }

    @RequestMapping("leave/{chatId}")
    public void leave(String accessHash, @PathVariable int chatId)
    {
        if (!accessService.checkAccessHash(accessHash))
            throw new BadRequestException("invalid Exception");
        telegramClients.leaveChannel(chatId);
    }
}

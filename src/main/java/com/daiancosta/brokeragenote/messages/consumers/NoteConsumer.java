package com.daiancosta.brokeragenote.messages.consumers;

import com.daiancosta.brokeragenote.domain.entities.Note;
import com.daiancosta.brokeragenote.domain.entities.messages.producers.NoteProducerMessage;
import com.daiancosta.brokeragenote.services.note.FileNoteService;
import com.daiancosta.brokeragenote.services.note.NoteService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Component
public class NoteConsumer {

    private final FileNoteService fileNoteService;
    private final NoteService noteService;

    @Autowired
    public NoteConsumer(final FileNoteService fileNoteService,
                        final NoteService noteService) {
        this.fileNoteService = fileNoteService;
        this.noteService = noteService;
    }

    @RabbitListener(queues = {"${brokerage.rabbitmq.queue}"})
    public void receive(@Payload NoteProducerMessage noteProducerMessage) {
        final InputStream file = new ByteArrayInputStream(noteProducerMessage.getFile());
        final Note note = fileNoteService.mapData(file, noteProducerMessage.getPassword());
        noteService.save(note);
    }
}

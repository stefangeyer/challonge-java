package com.exsoloscript.challonge;

import com.exsoloscript.challonge.guice.AttachmentTestModule;
import com.exsoloscript.challonge.guice.ChallongeTestModule;
import com.exsoloscript.challonge.guice.GuiceJUnitRunner;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.IOException;

@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({ChallongeTestModule.class, AttachmentTestModule.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SyncAttachmentTest {

    @Inject
    private ChallongeApi challongeApi;

    @Inject
    @Named("attachment")
    private File file;

    @Test
    public void createAttachmentTest() throws IOException, ChallongeException {
//        AttachmentQuery query = new AttachmentQuery.Builder()
////                .setAsset(this.file)
//                .setUrl("")
//                .setDescription("This is a test file")
//                .build();
//        Attachment attachment = this.challongeApi.attachments().createAttachment("mk4ahit", 40637548, query).sync();
//        assertEquals(attachment.assetFileName(), "testfile.txt");
    }


}

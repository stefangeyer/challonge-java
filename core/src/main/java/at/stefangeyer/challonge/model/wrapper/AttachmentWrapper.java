package at.stefangeyer.challonge.model.wrapper;

import at.stefangeyer.challonge.model.Attachment;

/**
 * Wrapper for easy JSON serialisation of an Attachment
 *
 * @author Stefan Geyer
 * @version 2018-07-08
 */
public class AttachmentWrapper {
    private Attachment match_attachment;

    public AttachmentWrapper(Attachment attachment) {
        this.match_attachment = attachment;
    }

    public Attachment getMatchAttachment() {
        return this.match_attachment;
    }
}

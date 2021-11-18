package com.rollas;

public interface IDocument {
    public boolean belongsSameApplicant(Object other);
    public String getId();
    public String getDocumentType();
}

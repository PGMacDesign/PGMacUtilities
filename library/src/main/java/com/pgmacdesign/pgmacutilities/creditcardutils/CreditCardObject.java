
package com.pgmacdesign.pgmacutilities.creditcardutils;


import java.util.Date;

/**
 * Represents a card (IE Credit, Debit) and contains information about the card
 */
public class CreditCardObject {

    private transient AccountNumber accountNumber;
    private transient Name name;
    private transient ExpirationDateObject expirationDateObject;
    private transient ServiceCode serviceCode;
    private transient CardConstants.CardMisc cardInfo;

    public void parseRawName(String rawName) {
        String[] fullName = rawName.split("/");
        if (fullName.length >= 2) {
            name = new Name(fullName[1], fullName[0]);
        } else {
            //Error parsing
        }
    }


    public CreditCardObject() {
        this(null);
    }

    public CreditCardObject(final AccountNumber accountNumber) {
        this(accountNumber, null);
    }

    public CreditCardObject(AccountNumber accountNumber, ExpirationDateObject expirationDateObject) {
        this(accountNumber, expirationDateObject, null);
    }

    public CreditCardObject(AccountNumber accountNumber,
                            ExpirationDateObject expirationDateObject, Name name) {
        this(accountNumber, expirationDateObject, name, null);
    }


    public CreditCardObject(AccountNumber accountNumber,
                            ExpirationDateObject expirationDateObject, Name name,
                            ServiceCode serviceCode) {
        if (accountNumber != null) {
            this.accountNumber = accountNumber;
        } else {
            this.accountNumber = new AccountNumber(null);
        }

        if (name != null) {
            this.name = name;
        } else {
            this.name = new Name();
        }

        if (expirationDateObject != null) {
            this.expirationDateObject = expirationDateObject;
        } else {
            this.expirationDateObject = new ExpirationDateObject();
        }

        if (serviceCode != null) {
            this.serviceCode = serviceCode;
        } else {
            this.serviceCode = new ServiceCode();
        }
    }

    public String getAccountNumber() {
        return accountNumber.getAccountNumber();
    }

    public String getCardHolderName() {
        return name.getFullName();
    }

    public ExpirationDateObject getExpirationDateObject() {
        return expirationDateObject;
    }

    public Date getExpirationDateAsDate() {
        return expirationDateObject.getExpirationDateAsDate();
    }

    public Name getName() {
        return name;
    }

    public AccountNumber getPrimaryAccountNumber() {
        return accountNumber;
    }

    public ServiceCode getServiceCode() {
        return serviceCode;
    }

    public boolean hasExpirationDate() {
        return expirationDateObject != null && expirationDateObject.hasExpirationDate();
    }

    public boolean hasPrimaryAccountNumber() {
        return accountNumber != null && accountNumber.hasAccountNumber();
    }

    public boolean hasServiceCode() {
        return serviceCode != null && serviceCode.hasServiceCode();
    }

    public boolean isExpired() {
        return expirationDateObject.isExpired();
    }

    public CardConstants.CardMisc getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CardConstants.CardMisc cardInfo) {
        this.cardInfo = cardInfo;
    }

}

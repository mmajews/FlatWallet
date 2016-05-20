package com.flat.wallet.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Currency;

@Entity
@Table(name = "prices")
public class Price extends EntityWithId {

	private Currency currency;

	private double cost;
}

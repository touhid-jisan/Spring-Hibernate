package com.touhid.spring5webapp.repositories;

import com.touhid.spring5webapp.model.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository  extends CrudRepository<Publisher, Long> {
}

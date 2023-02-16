package com.thiagov.restaurantsapi.repositories;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

abstract class CsvRepository<E> {
  private static final Logger LOGGER = Logger.getLogger(CsvRepository.class.getName());
  private List<E> items = new ArrayList<>();

  protected void readCsv(String csvUrl) {
    URL url;
    try {
      url = ResourceUtils.getURL(csvUrl);
    } catch (FileNotFoundException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
      return;
    }
    try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {

      List<String[]> collectedCsv = br.lines()
          .map(l -> l.split(",")).collect(Collectors.toList());

      // The CSV header needs to be removed
      collectedCsv.remove(0);
      collectedCsv.stream().forEach(csvLine -> items.add(instantiateObject(csvLine)));
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }
  }

  protected abstract E instantiateObject(String[] csvLine);

  public List<E> findAll() {
    return items;
  }
}

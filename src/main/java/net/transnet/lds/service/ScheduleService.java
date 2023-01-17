package net.transnet.lds.service;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/*
 * As long as the bean of the class instance that contains a scheduled method exists
 * The scheduler will ensure that the method gets executed.
 * 
 * Essentilly @Component below can be @Service, @Bean, @ e.t.c
 */
@Component
public class ScheduleService {
	// should print hello every 5000ms or 5s
	@Scheduled(fixedRate = 5000)
	public void printHello() {
		System.out.println("Hello, World!");
	}

	@Scheduled(fixedRate = 2000)
	public void watchTask() {
		try {
			WatchService watcher = FileSystems.getDefault().newWatchService();
			Path dir = Paths.get("C:\\Users\\OWen\\Documents\\workspace-spring-tool-suite\\locomotive-file-requestor\\downloads");
			dir.register(watcher, ENTRY_CREATE);
			System.out.println("Watch Service registered for dir: " + dir.getFileName());

			while (true) {
				WatchKey key;
				try {
					key = watcher.take();
				} catch (InterruptedException ex) {
					return;
				}

				for (WatchEvent<?> event : key.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();

					@SuppressWarnings("unchecked")
					WatchEvent<Path> ev = (WatchEvent<Path>) event;
					Path fileName = ev.context();
					
					if (kind == ENTRY_CREATE) {
						System.out.println(kind.name() + ": " + fileName);
						// Change status to started download
					}
					
					if (kind == ENTRY_MODIFY) {
						System.out.println(kind.name() + ": " + fileName);
						// Change status to download in progress if not already marked for in progress
					}
				}
				boolean valid = key.reset();
				if (!valid) {
					break;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}

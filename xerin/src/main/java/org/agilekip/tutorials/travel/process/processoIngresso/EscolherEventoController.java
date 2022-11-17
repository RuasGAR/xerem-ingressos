package org.agilekip.tutorials.travel.process.processoIngresso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/processo-ingresso/escolher-evento")
public class EscolherEventoController {

    private final Logger log = LoggerFactory.getLogger(EscolherEventoController.class);

    private final EscolherEventoService escolherEventoService;

    public EscolherEventoController(EscolherEventoService escolherEventoService) {
        this.escolherEventoService = escolherEventoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscolherEventoContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        EscolherEventoContextDTO escolherEventoContext = escolherEventoService.loadContext(id);
        return ResponseEntity.ok(escolherEventoContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<EscolherEventoContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        EscolherEventoContextDTO escolherEventoContext = escolherEventoService.claim(id);
        return ResponseEntity.ok(escolherEventoContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody EscolherEventoContextDTO escolherEventoContext) {
        log.debug("REST request to complete ProcessoIngresso.EscolherEvento {}", escolherEventoContext.getTaskInstance().getId());
        escolherEventoService.complete(escolherEventoContext);
        return ResponseEntity.noContent().build();
    }
}

package org.agilekip.tutorials.travel.process.processoIngresso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/processo-ingresso/escolher-setor")
public class EscolherSetorController {

    private final Logger log = LoggerFactory.getLogger(EscolherSetorController.class);

    private final EscolherSetorService escolherSetorService;

    public EscolherSetorController(EscolherSetorService escolherSetorService) {
        this.escolherSetorService = escolherSetorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscolherSetorContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        EscolherSetorContextDTO escolherSetorContext = escolherSetorService.loadContext(id);
        return ResponseEntity.ok(escolherSetorContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<EscolherSetorContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        EscolherSetorContextDTO escolherSetorContext = escolherSetorService.claim(id);
        return ResponseEntity.ok(escolherSetorContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody EscolherSetorContextDTO escolherSetorContext) {
        log.debug("REST request to complete ProcessoIngresso.EscolherSetor {}", escolherSetorContext.getTaskInstance().getId());
        escolherSetorService.complete(escolherSetorContext);
        return ResponseEntity.noContent().build();
    }
}

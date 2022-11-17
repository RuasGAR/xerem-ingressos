package org.agilekip.tutorials.travel.process.processoIngresso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/processo-ingresso/confirmacao")
public class ConfirmacaoController {

    private final Logger log = LoggerFactory.getLogger(ConfirmacaoController.class);

    private final ConfirmacaoService confirmacaoService;

    public ConfirmacaoController(ConfirmacaoService confirmacaoService) {
        this.confirmacaoService = confirmacaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfirmacaoContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        ConfirmacaoContextDTO confirmacaoContext = confirmacaoService.loadContext(id);
        return ResponseEntity.ok(confirmacaoContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<ConfirmacaoContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        ConfirmacaoContextDTO confirmacaoContext = confirmacaoService.claim(id);
        return ResponseEntity.ok(confirmacaoContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody ConfirmacaoContextDTO confirmacaoContext) {
        log.debug("REST request to complete ProcessoIngresso.Confirmacao {}", confirmacaoContext.getTaskInstance().getId());
        confirmacaoService.complete(confirmacaoContext);
        return ResponseEntity.noContent().build();
    }
}

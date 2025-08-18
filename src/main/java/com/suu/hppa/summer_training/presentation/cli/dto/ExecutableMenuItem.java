package com.suu.hppa.summer_training.presentation.cli.dto;

import com.suu.hppa.summer_training.presentation.cli.viewmodel.Menu;

public record ExecutableMenuItem(Menu menu, Runnable renderer) {
}

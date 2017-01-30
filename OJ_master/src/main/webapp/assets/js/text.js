CodeMirror.defineMode("text", function(config, parserConfig) {
  // Interface

  return {
    startState: function() {
      return {
        indented: 0,
      };
    },

    token: function(stream, state) {
      stream.skipToEnd();
      state.indented = stream.column();
      return null;
    },

    indent: function(state, textAfter) {
      return 0;
    },
  };
});

CodeMirror.defineMIME("text/plain", {name: "text"});

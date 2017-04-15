(function() {
    CodeMirror.extendMode("mysql", {
        commentStart: "/* ",
        commentEnd: " */",
        newlineAfterToken: function(type, content, textAfter) {
            return /^[{}]$/.test(content);
        }
    });

    // Comment/uncomment the specified range
    CodeMirror.defineExtension("commentRange", function (isComment, from, to) {
        var cm = this, curMode = CodeMirror.innerMode(cm.getMode(), cm.getTokenAt(from).state).mode;
        cm.operation(function() {
            if (isComment) { // Comment range
                cm.replaceRange(curMode.commentEnd, to);
                cm.replaceRange(curMode.commentStart, from);
                if (from.line == to.line && from.ch == to.ch) // An empty comment inserted - put cursor inside
                    cm.setCursor(from.line, from.ch + curMode.commentStart.length);
            } else { // Uncomment range
                var selText = cm.getRange(from, to);
                var startIndex = selText.indexOf(curMode.commentStart);
                var endIndex = selText.lastIndexOf(curMode.commentEnd);
                if (startIndex > -1 && endIndex > -1 && endIndex > startIndex) {
                    // Take string till comment start
                    selText = selText.substr(0, startIndex)
                        // From comment start till comment end
                            + selText.substring(startIndex + curMode.commentStart.length, endIndex)
                        // From comment end till string end
                            + selText.substr(endIndex + curMode.commentEnd.length);
                }
                cm.replaceRange(selText, from, to);
            }
        });
    });
})();

CC = gcc
CXX = g++
CFLAGS = -I. 
LFLAGS = -L. -Wl,-rpath=. -lgdi32 -lopengl32

BUILD_DIR = ./build
EXECNAME = EngineTest
OBJS = lib/GL_4_6_core.o GLWindow/GLWindow.o

RM ?= rm
MKDIR_P ?= mkdir -p

#if shared library target
#CFLAGS += -shared -undefined dynamic_lookup

#Build target executable
$(BUILD_DIR)/$(TARGET_EXEC): $(OBJS)
	$(CXX) $(OBJS) -o $@ $(LDFLAGS)

all: release

clean:
	$(RM) -r $(BUILD_DIR)

release: $(BUILD_DIR)/$(TARGET_EXEC)
	

debug: CFLAGS += -O0 -g3 #With flags for debugger
debug: release

#Generate Object files from C files
$(BUILD_DIR)/%.o: %.c
	$(MKDIR_P) $(dir $@)
	$(CC) $(CFLAGS) -c $< -o $@

#Generate Object files from C++ files
$(BUILD_DIR)/%.o: %.cpp
	$(MKDIR_P) $(dir $@)
	$(CXX) $(CFLAGS) -c $< -o $@

.PHONY: all clean release debug